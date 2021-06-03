///////////////////////////////////////////////////////////////
//  Copyright 2013 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// Generic routines for converting floating point values to and from decimal strings.
// Note that these use "naive" algorithms which result in rounding error - so they
// do not round trip to and from the string representation (but should only be out
// in the last bit).
//

#ifndef BOOST_MP_FLOAT_STRING_CVT_HPP
#define BOOST_MP_FLOAT_STRING_CVT_HPP

#include <cctype>

<<<<<<< HEAD
namespace boost{ namespace multiprecision{ namespace detail{
=======
namespace boost { namespace multiprecision { namespace detail {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

template <class I>
inline void round_string_up_at(std::string& s, int pos, I& expon)
{
   //
   // Rounds up a string representation of a number at pos:
   //
<<<<<<< HEAD
   if(pos < 0)
=======
   if (pos < 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      s.insert(static_cast<std::string::size_type>(0), 1, '1');
      s.erase(s.size() - 1);
      ++expon;
   }
<<<<<<< HEAD
   else if(s[pos] == '9')
=======
   else if (s[pos] == '9')
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      s[pos] = '0';
      round_string_up_at(s, pos - 1, expon);
   }
   else
   {
<<<<<<< HEAD
      if((pos == 0) && (s[pos] == '0') && (s.size() == 1))
=======
      if ((pos == 0) && (s[pos] == '0') && (s.size() == 1))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         ++expon;
      ++s[pos];
   }
}

template <class Backend>
std::string convert_to_string(Backend b, std::streamsize digits, std::ios_base::fmtflags f)
{
<<<<<<< HEAD
   using default_ops::eval_log10;
   using default_ops::eval_floor;
   using default_ops::eval_pow;
   using default_ops::eval_convert_to;
   using default_ops::eval_multiply;
   using default_ops::eval_divide;
   using default_ops::eval_subtract;
   using default_ops::eval_fpclassify;

   typedef typename mpl::front<typename Backend::unsigned_types>::type ui_type;
   typedef typename Backend::exponent_type exponent_type;

   std::string result;
   bool iszero = false;
   bool isneg = false;
   exponent_type expon = 0;
=======
   using default_ops::eval_convert_to;
   using default_ops::eval_divide;
   using default_ops::eval_floor;
   using default_ops::eval_fpclassify;
   using default_ops::eval_log10;
   using default_ops::eval_multiply;
   using default_ops::eval_pow;
   using default_ops::eval_subtract;

   typedef typename mpl::front<typename Backend::unsigned_types>::type ui_type;
   typedef typename Backend::exponent_type                             exponent_type;

   std::string     result;
   bool            iszero     = false;
   bool            isneg      = false;
   exponent_type   expon      = 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   std::streamsize org_digits = digits;
   BOOST_ASSERT(digits > 0);

   int fpt = eval_fpclassify(b);

<<<<<<< HEAD
   if(fpt == (int)FP_ZERO)
=======
   if (fpt == (int)FP_ZERO)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      result = "0";
      iszero = true;
   }
<<<<<<< HEAD
   else if(fpt == (int)FP_INFINITE)
   {
      if(b.compare(ui_type(0)) < 0)
=======
   else if (fpt == (int)FP_INFINITE)
   {
      if (b.compare(ui_type(0)) < 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         return "-inf";
      else
         return ((f & std::ios_base::showpos) == std::ios_base::showpos) ? "+inf" : "inf";
   }
<<<<<<< HEAD
   else if(fpt == (int)FP_NAN)
=======
   else if (fpt == (int)FP_NAN)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      return "nan";
   }
   else
   {
      //
      // Start by figuring out the exponent:
      //
      isneg = b.compare(ui_type(0)) < 0;
<<<<<<< HEAD
      if(isneg)
=======
      if (isneg)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         b.negate();
      Backend t;
      Backend ten;
      ten = ui_type(10);

      eval_log10(t, b);
      eval_floor(t, t);
      eval_convert_to(&expon, t);
<<<<<<< HEAD
      if(-expon > std::numeric_limits<number<Backend> >::max_exponent10 - 3)
      {
         int e = -expon / 2;
=======
      if (-expon > std::numeric_limits<number<Backend> >::max_exponent10 - 3)
      {
         int     e = -expon / 2;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         Backend t2;
         eval_pow(t2, ten, e);
         eval_multiply(t, t2, b);
         eval_multiply(t, t2);
<<<<<<< HEAD
         if(expon & 1)
=======
         if (expon & 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            eval_multiply(t, ten);
      }
      else
      {
         eval_pow(t, ten, -expon);
         eval_multiply(t, b);
      }
      //
      // Make sure we're between [1,10) and adjust if not:
      //
<<<<<<< HEAD
      if(t.compare(ui_type(1)) < 0)
=======
      if (t.compare(ui_type(1)) < 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         eval_multiply(t, ui_type(10));
         --expon;
      }
<<<<<<< HEAD
      else if(t.compare(ui_type(10)) >= 0)
=======
      else if (t.compare(ui_type(10)) >= 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         eval_divide(t, ui_type(10));
         ++expon;
      }
      Backend digit;
      ui_type cdigit;
      //
      // Adjust the number of digits required based on formatting options:
      //
<<<<<<< HEAD
      if(((f & std::ios_base::fixed) == std::ios_base::fixed) && (expon != -1))
         digits += expon + 1;
      if((f & std::ios_base::scientific) == std::ios_base::scientific)
=======
      if (((f & std::ios_base::fixed) == std::ios_base::fixed) && (expon != -1))
         digits += expon + 1;
      if ((f & std::ios_base::scientific) == std::ios_base::scientific)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         ++digits;
      //
      // Extract the digits one at a time:
      //
<<<<<<< HEAD
      for(unsigned i = 0; i < digits; ++i)
=======
      for (unsigned i = 0; i < digits; ++i)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         eval_floor(digit, t);
         eval_convert_to(&cdigit, digit);
         result += static_cast<char>('0' + cdigit);
         eval_subtract(t, digit);
         eval_multiply(t, ten);
      }
      //
      // Possibly round result:
      //
<<<<<<< HEAD
      if(digits >= 0)
=======
      if (digits >= 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         eval_floor(digit, t);
         eval_convert_to(&cdigit, digit);
         eval_subtract(t, digit);
<<<<<<< HEAD
         if((cdigit == 5) && (t.compare(ui_type(0)) == 0))
         {
            // Bankers rounding:
            if((*result.rbegin() - '0') & 1)
=======
         if ((cdigit == 5) && (t.compare(ui_type(0)) == 0))
         {
            // Bankers rounding:
            if ((*result.rbegin() - '0') & 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
               round_string_up_at(result, result.size() - 1, expon);
            }
         }
<<<<<<< HEAD
         else if(cdigit >= 5)
=======
         else if (cdigit >= 5)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         {
            round_string_up_at(result, result.size() - 1, expon);
         }
      }
   }
<<<<<<< HEAD
   while((result.size() > digits) && result.size())
   {
      // We may get here as a result of rounding...
      if(result.size() > 1)
         result.erase(result.size() - 1);
      else
      {
         if(expon > 0)
=======
   while ((result.size() > digits) && result.size())
   {
      // We may get here as a result of rounding...
      if (result.size() > 1)
         result.erase(result.size() - 1);
      else
      {
         if (expon > 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            --expon; // so we put less padding in the result.
         else
            ++expon;
         ++digits;
      }
   }
   BOOST_ASSERT(org_digits >= 0);
<<<<<<< HEAD
   if(isneg)
=======
   if (isneg)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      result.insert(static_cast<std::string::size_type>(0), 1, '-');
   format_float_string(result, expon, org_digits, f, iszero);

   return result;
}

template <class Backend>
void convert_from_string(Backend& b, const char* p)
{
<<<<<<< HEAD
   using default_ops::eval_multiply;
   using default_ops::eval_add;
   using default_ops::eval_pow;
   using default_ops::eval_divide;

   typedef typename mpl::front<typename Backend::unsigned_types>::type ui_type;
   b = ui_type(0);
   if(!p || (*p == 0))
      return;

   bool is_neg = false;
   bool is_neg_expon = false;
   static const ui_type ten = ui_type(10);
   typename Backend::exponent_type expon = 0;
   int digits_seen = 0;
   typedef std::numeric_limits<number<Backend, et_off> > limits;
   static const int max_digits = limits::is_specialized ? limits::max_digits10 + 1 : INT_MAX;

   if(*p == '+') ++p;
   else if(*p == '-')
=======
   using default_ops::eval_add;
   using default_ops::eval_divide;
   using default_ops::eval_multiply;
   using default_ops::eval_pow;

   typedef typename mpl::front<typename Backend::unsigned_types>::type ui_type;
   b = ui_type(0);
   if (!p || (*p == 0))
      return;

   bool                                                  is_neg       = false;
   bool                                                  is_neg_expon = false;
   static const ui_type                                  ten          = ui_type(10);
   typename Backend::exponent_type                       expon        = 0;
   int                                                   digits_seen  = 0;
   typedef std::numeric_limits<number<Backend, et_off> > limits;
   static const int                                      max_digits = limits::is_specialized ? limits::max_digits10 + 1 : INT_MAX;

   if (*p == '+')
      ++p;
   else if (*p == '-')
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      is_neg = true;
      ++p;
   }
<<<<<<< HEAD
   if((std::strcmp(p, "nan") == 0) || (std::strcmp(p, "NaN") == 0) || (std::strcmp(p, "NAN") == 0))
   {
      eval_divide(b, ui_type(0));
      if(is_neg)
         b.negate();
      return;
   }
   if((std::strcmp(p, "inf") == 0) || (std::strcmp(p, "Inf") == 0) || (std::strcmp(p, "INF") == 0))
   {
      b = ui_type(1);
      eval_divide(b, ui_type(0));
      if(is_neg)
=======
   if ((std::strcmp(p, "nan") == 0) || (std::strcmp(p, "NaN") == 0) || (std::strcmp(p, "NAN") == 0))
   {
      eval_divide(b, ui_type(0));
      if (is_neg)
         b.negate();
      return;
   }
   if ((std::strcmp(p, "inf") == 0) || (std::strcmp(p, "Inf") == 0) || (std::strcmp(p, "INF") == 0))
   {
      b = ui_type(1);
      eval_divide(b, ui_type(0));
      if (is_neg)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         b.negate();
      return;
   }
   //
   // Grab all the leading digits before the decimal point:
   //
<<<<<<< HEAD
   while(std::isdigit(*p))
=======
   while (std::isdigit(*p))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      eval_multiply(b, ten);
      eval_add(b, ui_type(*p - '0'));
      ++p;
      ++digits_seen;
   }
<<<<<<< HEAD
   if(*p == '.')
=======
   if (*p == '.')
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      //
      // Grab everything after the point, stop when we've seen
      // enough digits, even if there are actually more available:
      //
      ++p;
<<<<<<< HEAD
      while(std::isdigit(*p))
=======
      while (std::isdigit(*p))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         eval_multiply(b, ten);
         eval_add(b, ui_type(*p - '0'));
         ++p;
         --expon;
<<<<<<< HEAD
         if(++digits_seen > max_digits)
            break;
      }
      while(std::isdigit(*p))
=======
         if (++digits_seen > max_digits)
            break;
      }
      while (std::isdigit(*p))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         ++p;
   }
   //
   // Parse the exponent:
   //
<<<<<<< HEAD
   if((*p == 'e') || (*p == 'E'))
   {
      ++p;
      if(*p == '+') ++p;
      else if(*p == '-')
=======
   if ((*p == 'e') || (*p == 'E'))
   {
      ++p;
      if (*p == '+')
         ++p;
      else if (*p == '-')
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         is_neg_expon = true;
         ++p;
      }
      typename Backend::exponent_type e2 = 0;
<<<<<<< HEAD
      while(std::isdigit(*p))
=======
      while (std::isdigit(*p))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         e2 *= 10;
         e2 += (*p - '0');
         ++p;
      }
<<<<<<< HEAD
      if(is_neg_expon)
         e2 = -e2;
      expon += e2;
   }
   if(expon)
=======
      if (is_neg_expon)
         e2 = -e2;
      expon += e2;
   }
   if (expon)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      // Scale by 10^expon, note that 10^expon can be
      // outside the range of our number type, even though the
      // result is within range, if that looks likely, then split
      // the calculation in two:
      Backend t;
      t = ten;
<<<<<<< HEAD
      if(expon > limits::min_exponent10 + 2)
=======
      if (expon > limits::min_exponent10 + 2)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      {
         eval_pow(t, t, expon);
         eval_multiply(b, t);
      }
      else
      {
         eval_pow(t, t, expon + digits_seen + 1);
         eval_multiply(b, t);
         t = ten;
         eval_pow(t, t, -digits_seen - 1);
         eval_multiply(b, t);
      }
   }
<<<<<<< HEAD
   if(is_neg)
      b.negate();
   if(*p)
=======
   if (is_neg)
      b.negate();
   if (*p)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      // Unexpected input in string:
      BOOST_THROW_EXCEPTION(std::runtime_error("Unexpected characters in string being interpreted as a float128."));
   }
}

<<<<<<< HEAD
}}} // namespaces
=======
}}} // namespace boost::multiprecision::detail
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif
