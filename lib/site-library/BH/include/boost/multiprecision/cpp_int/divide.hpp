///////////////////////////////////////////////////////////////
//  Copyright 2012 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// Comparison operators for cpp_int_backend:
//
#ifndef BOOST_MP_CPP_INT_DIV_HPP
#define BOOST_MP_CPP_INT_DIV_HPP

<<<<<<< HEAD
namespace boost{ namespace multiprecision{ namespace backends{

template <class CppInt1, class CppInt2, class CppInt3>
void divide_unsigned_helper(
   CppInt1* result, 
   const CppInt2& x, 
   const CppInt3& y, 
   CppInt1& r)
{
   if(((void*)result == (void*)&x) || ((void*)&r == (void*)&x))
=======
namespace boost { namespace multiprecision { namespace backends {

template <class CppInt1, class CppInt2, class CppInt3>
BOOST_MP_CXX14_CONSTEXPR void divide_unsigned_helper(
    CppInt1*       result,
    const CppInt2& x,
    const CppInt3& y,
    CppInt1&       r)
{
   if (((void*)result == (void*)&x) || ((void*)&r == (void*)&x))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      CppInt2 t(x);
      divide_unsigned_helper(result, t, y, r);
      return;
   }
<<<<<<< HEAD
   if(((void*)result == (void*)&y) || ((void*)&r == (void*)&y))
=======
   if (((void*)result == (void*)&y) || ((void*)&r == (void*)&y))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      CppInt3 t(y);
      divide_unsigned_helper(result, x, t, r);
      return;
   }

   /*
    Very simple, fairly braindead long division.
    Start by setting the remainder equal to x, and the
    result equal to 0.  Then in each loop we calculate our
    "best guess" for how many times y divides into r,
    add our guess to the result, and subtract guess*y
    from the remainder r.  One wrinkle is that the remainder
    may go negative, in which case we subtract the current guess
    from the result rather than adding.  The value of the guess
    is determined by dividing the most-significant-limb of the
    current remainder by the most-significant-limb of y.

    Note that there are more efficient algorithms than this
    available, in particular see Knuth Vol 2.  However for small
    numbers of limbs this generally outperforms the alternatives
    and avoids the normalisation step which would require extra storage.
    */

<<<<<<< HEAD

   using default_ops::eval_subtract;

   if(result == &r)
=======
   using default_ops::eval_subtract;

   if (result == &r)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      CppInt1 rem;
      divide_unsigned_helper(result, x, y, rem);
      r = rem;
      return;
   }

   //
   // Find the most significant words of numerator and denominator.
   //
   limb_type y_order = y.size() - 1;

<<<<<<< HEAD
   if(y_order == 0)
=======
   if (y_order == 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      //
      // Only a single non-zero limb in the denominator, in this case
      // we can use a specialized divide-by-single-limb routine which is
      // much faster.  This also handles division by zero:
      //
      divide_unsigned_helper(result, x, y.limbs()[y_order], r);
      return;
   }

   typename CppInt2::const_limb_pointer px = x.limbs();
   typename CppInt3::const_limb_pointer py = y.limbs();

   limb_type r_order = x.size() - 1;
<<<<<<< HEAD
   if((r_order == 0) && (*px == 0))
   {
      // x is zero, so is the result:
      r = x;
      if(result)
=======
   if ((r_order == 0) && (*px == 0))
   {
      // x is zero, so is the result:
      r = x;
      if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         *result = x;
      return;
   }

   r = x;
   r.sign(false);
<<<<<<< HEAD
   if(result)
=======
   if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      *result = static_cast<limb_type>(0u);
   //
   // Check if the remainder is already less than the divisor, if so
   // we already have the result.  Note we try and avoid a full compare
   // if we can:
   //
<<<<<<< HEAD
   if(r_order <= y_order)
   {
      if((r_order < y_order) || (r.compare_unsigned(y) < 0))
=======
   if (r_order <= y_order)
   {
      if ((r_order < y_order) || (r.compare_unsigned(y) < 0))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         return;
      }
   }

   CppInt1 t;
<<<<<<< HEAD
   bool r_neg = false;
=======
   bool    r_neg = false;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

   //
   // See if we can short-circuit long division, and use basic arithmetic instead:
   //
<<<<<<< HEAD
   if(r_order == 0)
   {
      if(result)
=======
   if (r_order == 0)
   {
      if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         *result = px[0] / py[0];
      }
      r = px[0] % py[0];
      return;
   }
<<<<<<< HEAD
   else if(r_order == 1)
   {
      double_limb_type a, b;
      a = (static_cast<double_limb_type>(px[1]) << CppInt1::limb_bits) | px[0];
      b = y_order ? 
         (static_cast<double_limb_type>(py[1]) << CppInt1::limb_bits) | py[0] 
         : py[0];
      if(result)
=======
   else if (r_order == 1)
   {
      double_limb_type a = (static_cast<double_limb_type>(px[1]) << CppInt1::limb_bits) | px[0];
      double_limb_type b = y_order ? (static_cast<double_limb_type>(py[1]) << CppInt1::limb_bits) | py[0]
                  : py[0];
      if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         *result = a / b;
      }
      r = a % b;
      return;
   }
   //
   // prepare result:
   //
<<<<<<< HEAD
   if(result)
      result->resize(1 + r_order - y_order, 1 + r_order - y_order);
   typename CppInt1::const_limb_pointer prem = r.limbs();
   // This is initialised just to keep the compiler from emitting useless warnings later on:
   typename CppInt1::limb_pointer pr 
      = typename CppInt1::limb_pointer();
   if(result)
   {
      pr = result->limbs();
      for(unsigned i = 1; i < 1 + r_order - y_order; ++i)
=======
   if (result)
      result->resize(1 + r_order - y_order, 1 + r_order - y_order);
   typename CppInt1::const_limb_pointer prem = r.limbs();
   // This is initialised just to keep the compiler from emitting useless warnings later on:
   typename CppInt1::limb_pointer pr = typename CppInt1::limb_pointer();
   if (result)
   {
      pr = result->limbs();
      for (unsigned i = 1; i < 1 + r_order - y_order; ++i)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         pr[i] = 0;
   }
   bool first_pass = true;

   do
   {
      //
      // Calculate our best guess for how many times y divides into r:
      //
<<<<<<< HEAD
      limb_type guess;
      if((prem[r_order] <= py[y_order]) && (r_order > 0))
      {
         double_limb_type a, b, v;
         a = (static_cast<double_limb_type>(prem[r_order]) << CppInt1::limb_bits) | prem[r_order - 1];
         b = py[y_order];
         v = a / b;
         if(v > CppInt1::max_limb_value)
            guess = 1;
         else
=======
      limb_type guess = 1;
      if ((prem[r_order] <= py[y_order]) && (r_order > 0))
      {
         double_limb_type a = (static_cast<double_limb_type>(prem[r_order]) << CppInt1::limb_bits) | prem[r_order - 1];
         double_limb_type b = py[y_order];
         double_limb_type v = a / b;
         if (v <= CppInt1::max_limb_value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         {
            guess = static_cast<limb_type>(v);
            --r_order;
         }
      }
<<<<<<< HEAD
      else if(r_order == 0)
=======
      else if (r_order == 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         guess = prem[0] / py[y_order];
      }
      else
      {
<<<<<<< HEAD
         double_limb_type a, b, v;
         a = (static_cast<double_limb_type>(prem[r_order]) << CppInt1::limb_bits) | prem[r_order - 1];
         b = (y_order > 0) ? (static_cast<double_limb_type>(py[y_order]) << CppInt1::limb_bits) | py[y_order - 1] : (static_cast<double_limb_type>(py[y_order])  << CppInt1::limb_bits);
         v = a / b;
=======
         double_limb_type a = (static_cast<double_limb_type>(prem[r_order]) << CppInt1::limb_bits) | prem[r_order - 1];
         double_limb_type b = (y_order > 0) ? (static_cast<double_limb_type>(py[y_order]) << CppInt1::limb_bits) | py[y_order - 1] : (static_cast<double_limb_type>(py[y_order]) << CppInt1::limb_bits);
         double_limb_type v = a / b;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         guess = static_cast<limb_type>(v);
      }
      BOOST_ASSERT(guess); // If the guess ever gets to zero we go on forever....
      //
      // Update result:
      //
      limb_type shift = r_order - y_order;
<<<<<<< HEAD
      if(result)
      {
         if(r_neg)
         {
            if(pr[shift] > guess)
=======
      if (result)
      {
         if (r_neg)
         {
            if (pr[shift] > guess)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
               pr[shift] -= guess;
            else
            {
               t.resize(shift + 1, shift + 1);
               t.limbs()[shift] = guess;
<<<<<<< HEAD
               for(unsigned i = 0; i < shift; ++i)
=======
               for (unsigned i = 0; i < shift; ++i)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                  t.limbs()[i] = 0;
               eval_subtract(*result, t);
            }
         }
<<<<<<< HEAD
         else if(CppInt1::max_limb_value - pr[shift] > guess)
=======
         else if (CppInt1::max_limb_value - pr[shift] > guess)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            pr[shift] += guess;
         else
         {
            t.resize(shift + 1, shift + 1);
            t.limbs()[shift] = guess;
<<<<<<< HEAD
            for(unsigned i = 0; i < shift; ++i)
=======
            for (unsigned i = 0; i < shift; ++i)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
               t.limbs()[i] = 0;
            eval_add(*result, t);
         }
      }
      //
      // Calculate guess * y, we use a fused mutiply-shift O(N) for this
      // rather than a full O(N^2) multiply:
      //
      double_limb_type carry = 0;
      t.resize(y.size() + shift + 1, y.size() + shift);
<<<<<<< HEAD
      bool truncated_t = (t.size() != y.size() + shift + 1);
      typename CppInt1::limb_pointer pt = t.limbs();
      for(unsigned i = 0; i < shift; ++i)
         pt[i] = 0;
      for(unsigned i = 0; i < y.size(); ++i)
=======
      bool                           truncated_t = (t.size() != y.size() + shift + 1);
      typename CppInt1::limb_pointer pt          = t.limbs();
      for (unsigned i = 0; i < shift; ++i)
         pt[i] = 0;
      for (unsigned i = 0; i < y.size(); ++i)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         carry += static_cast<double_limb_type>(py[i]) * static_cast<double_limb_type>(guess);
#ifdef __MSVC_RUNTIME_CHECKS
         pt[i + shift] = static_cast<limb_type>(carry & ~static_cast<limb_type>(0));
#else
<<<<<<< HEAD
         pt[i + shift] = static_cast<limb_type>(carry);
#endif
         carry >>= CppInt1::limb_bits;
      }
      if(carry && !truncated_t)
=======
         pt[i + shift]    = static_cast<limb_type>(carry);
#endif
         carry >>= CppInt1::limb_bits;
      }
      if (carry && !truncated_t)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
#ifdef __MSVC_RUNTIME_CHECKS
         pt[t.size() - 1] = static_cast<limb_type>(carry & ~static_cast<limb_type>(0));
#else
         pt[t.size() - 1] = static_cast<limb_type>(carry);
#endif
      }
<<<<<<< HEAD
      else if(!truncated_t)
=======
      else if (!truncated_t)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         t.resize(t.size() - 1, t.size() - 1);
      }
      //
      // Update r in a way that won't actually produce a negative result
      // in case the argument types are unsigned:
      //
<<<<<<< HEAD
      if(truncated_t && carry)
=======
      if (truncated_t && carry)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         // We need to calculate 2^n + t - r
         // where n is the number of bits in this type.
         // Simplest way is to get 2^n - r by complementing
         // r, then add t to it.  Note that we can't call eval_complement
         // in case this is a signed checked type:
<<<<<<< HEAD
         for(unsigned i = 0; i <= r_order; ++i)
=======
         for (unsigned i = 0; i <= r_order; ++i)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            r.limbs()[i] = ~prem[i];
         r.normalize();
         eval_increment(r);
         eval_add(r, t);
         r_neg = !r_neg;
      }
<<<<<<< HEAD
      else if(r.compare(t) > 0)
=======
      else if (r.compare(t) > 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         eval_subtract(r, t);
      }
      else
      {
         r.swap(t);
         eval_subtract(r, t);
<<<<<<< HEAD
         prem = r.limbs();
=======
         prem  = r.limbs();
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         r_neg = !r_neg;
      }
      //
      // First time through we need to strip any leading zero, otherwise
      // the termination condition goes belly-up:
      //
<<<<<<< HEAD
      if(result && first_pass)
      {
         first_pass = false;
         while(pr[result->size() - 1] == 0)
=======
      if (result && first_pass)
      {
         first_pass = false;
         while (pr[result->size() - 1] == 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            result->resize(result->size() - 1, result->size() - 1);
      }
      //
      // Update r_order:
      //
      r_order = r.size() - 1;
<<<<<<< HEAD
      if(r_order < y_order)
=======
      if (r_order < y_order)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         break;
   }
   // Termination condition is really just a check that r > y, but with a common
   // short-circuit case handled first:
<<<<<<< HEAD
   while((r_order > y_order) || (r.compare_unsigned(y) >= 0));
=======
   while ((r_order > y_order) || (r.compare_unsigned(y) >= 0));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

   //
   // We now just have to normalise the result:
   //
<<<<<<< HEAD
   if(r_neg && eval_get_sign(r))
   {
      // We have one too many in the result:
      if(result)
         eval_decrement(*result);
      if(y.sign())
=======
   if (r_neg && eval_get_sign(r))
   {
      // We have one too many in the result:
      if (result)
         eval_decrement(*result);
      if (y.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         r.negate();
         eval_subtract(r, y);
      }
      else
         eval_subtract(r, y, r);
   }

   BOOST_ASSERT(r.compare_unsigned(y) < 0); // remainder must be less than the divisor or our code has failed
}

template <class CppInt1, class CppInt2>
<<<<<<< HEAD
void divide_unsigned_helper(
   CppInt1* result, 
   const CppInt2& x, 
   limb_type y, 
   CppInt1& r)
{
   if(((void*)result == (void*)&x) || ((void*)&r == (void*)&x))
=======
BOOST_MP_CXX14_CONSTEXPR void divide_unsigned_helper(
    CppInt1*       result,
    const CppInt2& x,
    limb_type      y,
    CppInt1&       r)
{
   if (((void*)result == (void*)&x) || ((void*)&r == (void*)&x))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      CppInt2 t(x);
      divide_unsigned_helper(result, t, y, r);
      return;
   }

<<<<<<< HEAD
   if(result == &r)
=======
   if (result == &r)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      CppInt1 rem;
      divide_unsigned_helper(result, x, y, rem);
      r = rem;
      return;
   }

   // As above, but simplified for integer divisor:

   using default_ops::eval_subtract;

<<<<<<< HEAD
   if(y == 0)
=======
   if (y == 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      BOOST_THROW_EXCEPTION(std::overflow_error("Integer Division by zero."));
   }
   //
   // Find the most significant word of numerator.
   //
   limb_type r_order = x.size() - 1;

   //
   // Set remainder and result to their initial values:
   //
   r = x;
   r.sign(false);
   typename CppInt1::limb_pointer pr = r.limbs();

   //
<<<<<<< HEAD
   // check for x < y, try to do this without actually having to 
   // do a full comparison:
   //
   if((r_order == 0) && (*pr < y))
   {
      if(result)
=======
   // check for x < y, try to do this without actually having to
   // do a full comparison:
   //
   if ((r_order == 0) && (*pr < y))
   {
      if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         *result = static_cast<limb_type>(0u);
      return;
   }

   //
   // See if we can short-circuit long division, and use basic arithmetic instead:
   //
<<<<<<< HEAD
   if(r_order == 0)
   {
      if(result)
=======
   if (r_order == 0)
   {
      if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         *result = *pr / y;
         result->sign(x.sign());
      }
      *pr %= y;
      r.sign(x.sign());
      return;
   }
<<<<<<< HEAD
   else if(r_order == 1)
   {
      double_limb_type a;
      a = (static_cast<double_limb_type>(pr[r_order]) << CppInt1::limb_bits) | pr[0];
      if(result)
=======
   else if (r_order == 1)
   {
      double_limb_type a = (static_cast<double_limb_type>(pr[r_order]) << CppInt1::limb_bits) | pr[0];
      if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         *result = a / y;
         result->sign(x.sign());
      }
      r = a % y;
      r.sign(x.sign());
      return;
   }

   // This is initialised just to keep the compiler from emitting useless warnings later on:
   typename CppInt1::limb_pointer pres = typename CppInt1::limb_pointer();
<<<<<<< HEAD
   if(result)
   {
      result->resize(r_order + 1, r_order + 1);
      pres = result->limbs();
      if(result->size() > r_order)
         pres[r_order] = 0;  // just in case we don't set the most significant limb below.
=======
   if (result)
   {
      result->resize(r_order + 1, r_order + 1);
      pres = result->limbs();
      if (result->size() > r_order)
         pres[r_order] = 0; // just in case we don't set the most significant limb below.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   }

   do
   {
      //
      // Calculate our best guess for how many times y divides into r:
      //
<<<<<<< HEAD
      if((pr[r_order] < y) && r_order)
      {
         double_limb_type a, b;
         a = (static_cast<double_limb_type>(pr[r_order]) << CppInt1::limb_bits) | pr[r_order - 1];
         b = a % y;
         r.resize(r.size() - 1, r.size() - 1);
         --r_order;
         pr[r_order] = static_cast<limb_type>(b);
         if(result)
            pres[r_order] = static_cast<limb_type>(a / y);
         if(r_order && pr[r_order] == 0)
         {
            --r_order;  // No remainder, division was exact.
            r.resize(r.size() - 1, r.size() - 1);
            if(result)
=======
      if ((pr[r_order] < y) && r_order)
      {
         double_limb_type a = (static_cast<double_limb_type>(pr[r_order]) << CppInt1::limb_bits) | pr[r_order - 1];
         double_limb_type b = a % y;
         r.resize(r.size() - 1, r.size() - 1);
         --r_order;
         pr[r_order] = static_cast<limb_type>(b);
         if (result)
            pres[r_order] = static_cast<limb_type>(a / y);
         if (r_order && pr[r_order] == 0)
         {
            --r_order; // No remainder, division was exact.
            r.resize(r.size() - 1, r.size() - 1);
            if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
               pres[r_order] = static_cast<limb_type>(0u);
         }
      }
      else
      {
<<<<<<< HEAD
         if(result)
            pres[r_order] = pr[r_order] / y;
         pr[r_order] %= y;
         if(r_order && pr[r_order] == 0)
         {
            --r_order;  // No remainder, division was exact.
            r.resize(r.size() - 1, r.size() - 1);
            if(result)
=======
         if (result)
            pres[r_order] = pr[r_order] / y;
         pr[r_order] %= y;
         if (r_order && pr[r_order] == 0)
         {
            --r_order; // No remainder, division was exact.
            r.resize(r.size() - 1, r.size() - 1);
            if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
               pres[r_order] = static_cast<limb_type>(0u);
         }
      }
   }
   // Termination condition is really just a check that r >= y, but with two common
   // short-circuit cases handled first:
<<<<<<< HEAD
   while(r_order || (pr[r_order] >= y));

   if(result)
=======
   while (r_order || (pr[r_order] >= y));

   if (result)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      result->normalize();
      result->sign(x.sign());
   }
   r.normalize();
   r.sign(x.sign());

   BOOST_ASSERT(r.compare(y) < 0); // remainder must be less than the divisor or our code has failed
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2, unsigned MinBits3, unsigned MaxBits3, cpp_integer_type SignType3, cpp_int_check_type Checked3, class Allocator3>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3> >::value >::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a, 
      const cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3>& b)
{
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> r;
   bool s = a.sign() != b.sign();
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3> >::value>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a,
    const cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3>& b)
{
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> r;
   bool                                                                 s = a.sign() != b.sign();
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   divide_unsigned_helper(&result, a, b, r);
   result.sign(s);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value >::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a, 
      limb_type& b)
{
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> r;
   bool s = a.sign();
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a,
    limb_type&                                                                  b)
{
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> r;
   bool                                                                 s = a.sign();
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   divide_unsigned_helper(&result, a, b, r);
   result.sign(s);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value >::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a, 
      signed_limb_type& b)
{
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> r;
   bool s = a.sign() != (b < 0);
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a,
    signed_limb_type&                                                           b)
{
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> r;
   bool                                                                 s = a.sign() != (b < 0);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   divide_unsigned_helper(&result, a, static_cast<limb_type>(boost::multiprecision::detail::unsigned_abs(b)), r);
   result.sign(s);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value >::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& b)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& b)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   // There is no in place divide:
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> a(result);
   eval_divide(result, a, b);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      limb_type b)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result,
    limb_type                                                             b)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   // There is no in place divide:
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> a(result);
   eval_divide(result, a, b);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      signed_limb_type b)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result,
    signed_limb_type                                                      b)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   // There is no in place divide:
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> a(result);
   eval_divide(result, a, b);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2, unsigned MinBits3, unsigned MaxBits3, cpp_integer_type SignType3, cpp_int_check_type Checked3, class Allocator3>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3> >::value >::type
   eval_modulus(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a, 
      const cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3>& b)
{
   bool s = a.sign();
   divide_unsigned_helper(static_cast<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>* >(0), a, b, result);
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3> >::value>::type
eval_modulus(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a,
    const cpp_int_backend<MinBits3, MaxBits3, SignType3, Checked3, Allocator3>& b)
{
   bool s = a.sign();
   divide_unsigned_helper(static_cast<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>*>(0), a, b, result);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   result.sign(s);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value >::type 
   eval_modulus(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a, limb_type b)
{
   bool s = a.sign();
   divide_unsigned_helper(static_cast<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>* >(0), a, b, result);
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value>::type
eval_modulus(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a, limb_type b)
{
   bool s = a.sign();
   divide_unsigned_helper(static_cast<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>*>(0), a, b, result);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   result.sign(s);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value >::type 
   eval_modulus(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a, 
      signed_limb_type b)
{
   bool s = a.sign();
   divide_unsigned_helper(static_cast<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>* >(0), a, static_cast<limb_type>(boost::multiprecision::detail::unsigned_abs(b)), result);
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value>::type
eval_modulus(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& a,
    signed_limb_type                                                            b)
{
   bool s = a.sign();
   divide_unsigned_helper(static_cast<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>*>(0), a, static_cast<limb_type>(boost::multiprecision::detail::unsigned_abs(b)), result);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   result.sign(s);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value >::type 
   eval_modulus(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& b)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value>::type
eval_modulus(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& b)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   // There is no in place divide:
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> a(result);
   eval_modulus(result, a, b);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type 
   eval_modulus(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      limb_type b)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type
eval_modulus(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result,
    limb_type                                                             b)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   // There is no in place divide:
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> a(result);
   eval_modulus(result, a, b);
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type 
   eval_modulus(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      signed_limb_type b)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<!is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type
eval_modulus(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result,
    signed_limb_type                                                      b)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   // There is no in place divide:
   cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> a(result);
   eval_modulus(result, a, b);
}

//
// Over again for trivial cpp_int's:
//
template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
         is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value 
         && is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value
         && (is_signed_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value 
            || is_signed_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value)
         >::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& o)
{
   if(!*o.limbs())
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && (is_signed_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value || is_signed_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value)>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& o)
{
   if (!*o.limbs())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      BOOST_THROW_EXCEPTION(std::overflow_error("Division by zero."));
   *result.limbs() /= *o.limbs();
   result.sign(result.sign() != o.sign());
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
         is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value 
         && is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value
         && is_unsigned_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value 
         && is_unsigned_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value
      >::type 
   eval_divide(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& o)
{
   if(!*o.limbs())
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && is_unsigned_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && is_unsigned_number<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type
eval_divide(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& o)
{
   if (!*o.limbs())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      BOOST_THROW_EXCEPTION(std::overflow_error("Division by zero."));
   *result.limbs() /= *o.limbs();
}

template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
         is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value 
         && is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value
      >::type 
   eval_modulus(
      cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& result, 
      const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& o)
{
   if(!*o.limbs())
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value>::type
eval_modulus(
    cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>&       result,
    const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& o)
{
   if (!*o.limbs())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      BOOST_THROW_EXCEPTION(std::overflow_error("Division by zero."));
   *result.limbs() %= *o.limbs();
   result.sign(result.sign());
}

<<<<<<< HEAD
}}} // namespaces
=======
}}} // namespace boost::multiprecision::backends
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif
